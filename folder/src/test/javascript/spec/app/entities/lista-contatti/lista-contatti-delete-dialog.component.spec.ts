/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MyappjhTestModule } from '../../../test.module';
import { ListaContattiDeleteDialogComponent } from 'app/entities/lista-contatti/lista-contatti-delete-dialog.component';
import { ListaContattiService } from 'app/entities/lista-contatti/lista-contatti.service';

describe('Component Tests', () => {
    describe('ListaContatti Management Delete Component', () => {
        let comp: ListaContattiDeleteDialogComponent;
        let fixture: ComponentFixture<ListaContattiDeleteDialogComponent>;
        let service: ListaContattiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [ListaContattiDeleteDialogComponent]
            })
                .overrideTemplate(ListaContattiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ListaContattiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ListaContattiService);
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
