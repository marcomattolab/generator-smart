/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyappjhTestModule } from '../../../test.module';
import { GeolocalizzazioneDeleteDialogComponent } from 'app/entities/geolocalizzazione/geolocalizzazione-delete-dialog.component';
import { GeolocalizzazioneService } from 'app/entities/geolocalizzazione/geolocalizzazione.service';

describe('Component Tests', () => {
    describe('Geolocalizzazione Management Delete Component', () => {
        let comp: GeolocalizzazioneDeleteDialogComponent;
        let fixture: ComponentFixture<GeolocalizzazioneDeleteDialogComponent>;
        let service: GeolocalizzazioneService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [GeolocalizzazioneDeleteDialogComponent]
            })
                .overrideTemplate(GeolocalizzazioneDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GeolocalizzazioneDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GeolocalizzazioneService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
