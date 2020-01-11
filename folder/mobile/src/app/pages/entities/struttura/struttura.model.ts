import { BaseEntity } from 'src/model/base-entity';

export const enum Category {
    'Vitto',
    'Alloggio',
    'Altro'
}

export class Struttura implements BaseEntity {
    constructor(
        public id?: number,
        public categoria?: Category,
        public codice?: string,
        public nome?: string,
        public telefono?: string,
        public email?: string,
        public mediaValutazione?: number,
        public numeroValutazioni?: number,
        public isActive?: boolean,
        public sedeId?: number,
    ) {
        this.isActive = false;
    }
}
