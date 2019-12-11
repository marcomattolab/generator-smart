/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { AcquirenteDetailComponent } from 'app/entities/acquirente/acquirente-detail.component';
import { Acquirente } from 'app/shared/model/acquirente.model';

describe('Component Tests', () => {
    describe('Acquirente Management Detail Component', () => {
        let comp: AcquirenteDetailComponent;
        let fixture: ComponentFixture<AcquirenteDetailComponent>;
        const route = ({ data: of({ acquirente: new Acquirente(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [AcquirenteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AcquirenteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcquirenteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.acquirente).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
