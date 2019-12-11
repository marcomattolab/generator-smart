/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { MyappjhTestModule } from '../../../test.module';
import { ListaContattiComponent } from 'app/entities/lista-contatti/lista-contatti.component';
import { ListaContattiService } from 'app/entities/lista-contatti/lista-contatti.service';
import { ListaContatti } from 'app/shared/model/lista-contatti.model';

describe('Component Tests', () => {
    describe('ListaContatti Management Component', () => {
        let comp: ListaContattiComponent;
        let fixture: ComponentFixture<ListaContattiComponent>;
        let service: ListaContattiService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [MyappjhTestModule],
                declarations: [ListaContattiComponent],
                providers: []
            })
                .overrideTemplate(ListaContattiComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ListaContattiComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ListaContattiService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ListaContatti(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.listaContattis[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
