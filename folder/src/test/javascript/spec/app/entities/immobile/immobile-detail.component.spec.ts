/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { ImmobileDetailComponent } from 'app/entities/immobile/immobile-detail.component';
import { Immobile } from 'app/shared/model/immobile.model';

describe('Component Tests', () => {
    describe('Immobile Management Detail Component', () => {
        let comp: ImmobileDetailComponent;
        let fixture: ComponentFixture<ImmobileDetailComponent>;
        const route = ({ data: of({ immobile: new Immobile(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [ImmobileDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ImmobileDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImmobileDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.immobile).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
