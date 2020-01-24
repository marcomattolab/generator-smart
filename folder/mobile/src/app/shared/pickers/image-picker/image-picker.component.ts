import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild, ViewEncapsulation} from '@angular/core';
import {CameraResultType, CameraSource, Capacitor, Plugins} from '@capacitor/core';
import {Platform, ToastController} from '@ionic/angular';
import {AndroidPermissions} from '@ionic-native/android-permissions/ngx';
import {Camera} from '@ionic-native/camera/ngx';

@Component({
  selector: 'app-image-picker',
  templateUrl: './image-picker.component.html',
  styleUrls: ['./image-picker.component.scss']
})
export class ImagePickerComponent implements OnInit {
  @ViewChild('filePicker', {static: false}) filePickerRef: ElementRef<HTMLInputElement>;
  @Output() imagePick = new EventEmitter<string | File>();
  @Input() attachMent: { format?, content? } = null;
  selectedImage: string;
  usePicker = false;

  constructor(
    private platform: Platform,
    private camera: Camera,
    private toastController: ToastController,
    private androidPermissions: AndroidPermissions) {
  }

  ngOnInit() {
    if (
      (this.platform.is('mobile') && !this.platform.is('hybrid')) ||
      this.platform.is('desktop')
    ) {
      this.usePicker = true;
    }

    if (this.attachMent) {
      this.setImage(this.attachMent.format, this.attachMent.content);
    } else {
      this.attachMent = {};
    }
  }

  private setImage(format: string, imageContent: string) {
    this.selectedImage = 'data:' + format + ';base64,' + imageContent;

    this.attachMent = this.attachMent || {};
    this.attachMent.format = format;
    this.attachMent.content = imageContent;
  }

  onPickImage() {
    if (!Capacitor.isPluginAvailable('Camera')) {
      this.filePickerRef.nativeElement.click();
      return;
    }

    if (this.platform.is('android')) {
      this.androidPermissions.checkPermission(this.androidPermissions.PERMISSION.CAMERA).then(
        result => this.openCamera(), // permission granted
        err => this.androidPermissions.requestPermission(this.androidPermissions.PERMISSION.CAMERA)
      );
    } else {
      this.openCamera();
    }
  }

  private openCamera() {
    // Plugins.Camera.getPhoto({
    //   quality: 25,
    //   source: CameraSource.Prompt,
    //   correctOrientation: true,
    //   // height: 320,
    //   width: 300,
    //   resultType: CameraResultType.Base64
    // }).then(image => {
    //   this.setImage('image/' + image.format, image.base64String);
    //   this.imagePick.emit(image.base64String);
    // }).catch(error => {
    //   console.log(error);
    //   if (this.usePicker) {
    //     this.filePickerRef.nativeElement.click();
    //   }
    //   return false;
    // });

    if (Camera.installed()) {
      const cameraOptions = {
        quality: 100,
        targetWidth: 900,
        targetHeight: 600,
        destinationType: this.camera.DestinationType.DATA_URL,
        encodingType: this.camera.EncodingType.JPEG,
        mediaType: this.camera.MediaType.PICTURE,
        saveToPhotoAlbum: false,
        allowEdit: true,
        sourceType: 1
      };

      this.camera.getPicture(cameraOptions).then((data) => {
        // console.log('camera getPicture');
        const JPEG = 'jpeg';
        const JPEGImageFormat = 'image/' + JPEG;

        this.setImage(JPEGImageFormat, data);
        this.imagePick.emit(data);
      }, async (err) => {
        const toast = await this.toastController.create({
          message: err,
          duration: 2000,
          position: 'top'
        });
        toast.present();
        alert('Unable to take photo');
      });
    } else {
      if (this.usePicker) {
        this.filePickerRef.nativeElement.click();
      }
    }
  }

  onFileChosen(event: Event) {
    const pickedFile = (event.target as HTMLInputElement).files[0];
    if (!pickedFile) {
      return;
    }

    this.attachMent.format = pickedFile.type;

    const fr = new FileReader();
    fr.onload = (data) => {
      const dataUrl = fr.result.toString();
      this.attachMent.content = dataUrl
        .replace('data:image/png;base64,', '')
        .replace('data:image/jpeg;base64,', '');
      this.selectedImage = dataUrl;
      this.imagePick.emit(pickedFile);
    };
    fr.readAsDataURL(pickedFile);
  }
}
