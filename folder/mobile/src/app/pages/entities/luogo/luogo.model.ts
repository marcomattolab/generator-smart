import { BaseEntity } from 'src/model/base-entity';

export class Luogo implements BaseEntity {
    constructor(
        public id?: number,
        public indirizzo?: string,
        public citta?: string,
        public cap?: string,
        public nazione?: string,
        public latitudine?: number,
        public longitudine?: number,
    ) {
    }
}
