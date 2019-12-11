/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MyappjhTestModule } from '../../../test.module';
import { ListaContattiDetailComponent } from 'app/entities/lista-contatti/lista-contatti-detail.component';
import { ListaContatti } from 'app/shared/model/lista-contatti.model';

describe('Component Tests', () => {
    describe('ListaContatti Management Detail Component', () => {
        let comp: ListaContattiDetailComponent;
        let fixture: ComponentFixture<ListaContattiDetailComponent>;
        const route = ({ data: of({ listaContatti: new ListaContatti(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [ListaContattiDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ListaContattiDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ListaContattiDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.listaContatti).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
