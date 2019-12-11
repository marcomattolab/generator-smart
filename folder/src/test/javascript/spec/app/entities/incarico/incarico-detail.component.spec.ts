/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { IncaricoDetailComponent } from 'app/entities/incarico/incarico-detail.component';
import { Incarico } from 'app/shared/model/incarico.model';

describe('Component Tests', () => {
    describe('Incarico Management Detail Component', () => {
        let comp: IncaricoDetailComponent;
        let fixture: ComponentFixture<IncaricoDetailComponent>;
        const route = ({ data: of({ incarico: new Incarico(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [IncaricoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(IncaricoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(IncaricoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.incarico).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
