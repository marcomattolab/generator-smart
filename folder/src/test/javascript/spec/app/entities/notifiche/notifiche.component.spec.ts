/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyappjhTestModule } from '../../../test.module';
import { NotificheComponent } from 'app/entities/notifiche/notifiche.component';
import { NotificheService } from 'app/entities/notifiche/notifiche.service';
import { Notifiche } from 'app/shared/model/notifiche.model';

describe('Component Tests', () => {
    describe('Notifiche Management Component', () => {
        let comp: NotificheComponent;
        let fixture: ComponentFixture<NotificheComponent>;
        let service: NotificheService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [NotificheComponent],
                providers: []
            })
                .overrideTemplate(NotificheComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(NotificheComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NotificheService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Notifiche(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.notifiches[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
