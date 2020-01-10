import { BaseEntity } from 'src/model/base-entity';
import { Giustificativo } from '../giustificativo/giustificativo.model';
import {Moment} from 'moment';

export const enum CategoriaSpesa {
    'VITTO',
    'ALLOGGIO',
    'TRASPORTO',
    'RAPPRESENTANZA',
    'ALTRO'
}

export const enum SottocategoriaSpesa {
    'PRANZO',
    'CENA',
    'TAXI',
    'TRENO',
    'BUS',
    'AEREO',
    'NOLEGGIO',
    'ALTRO'
}

export const enum Stato {
    'BOZZA',
    'SOTTOPOSTA',
    'VALIDATA',
    'RIFIUTATA'
}

export class Spesa implements BaseEntity {
    constructor(
        public id?: number,
        public categoria?: CategoriaSpesa,
        public sottocategoria?: SottocategoriaSpesa,
        public importoSpesa?: number,
        public dataSpesa?: Moment,
        public stato?: Stato,
        public approvedDate?: Moment,
        public giustificativoSpesas?: Giustificativo[],
        public trasfertaId?: number,
        public structureId?: number,
    ) {
    }
}
