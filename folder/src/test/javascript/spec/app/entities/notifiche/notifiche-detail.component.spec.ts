/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { NotificheDetailComponent } from 'app/entities/notifiche/notifiche-detail.component';
import { Notifiche } from 'app/shared/model/notifiche.model';

describe('Component Tests', () => {
    describe('Notifiche Management Detail Component', () => {
        let comp: NotificheDetailComponent;
        let fixture: ComponentFixture<NotificheDetailComponent>;
        const route = ({ data: of({ notifiche: new Notifiche(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [NotificheDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(NotificheDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NotificheDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.notifiche).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
