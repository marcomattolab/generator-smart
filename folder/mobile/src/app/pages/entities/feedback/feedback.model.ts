import { BaseEntity } from 'src/model/base-entity';
import {Moment} from 'moment';

export const enum Category {
    'Vitto',
    'Alloggio',
    'Altro'
}

export class Feedback implements BaseEntity {
    constructor(
        public id?: number,
        public categoria?: Category,
        public descrizione?: string,
        public valutazione?: number,
        public dataValutazione?: Moment,
        public allegatoContentType?: string,
        public allegato?: any,
        public createdBy?: string,
        public aiNote?: string,
        public strutturaId?: number,
    ) {
    }
}
