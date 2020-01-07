import { BaseEntity } from 'src/model/base-entity';

export const enum Stato {
    'BOZZA',
    'SOTTOPOSTA',
    'VALIDATA',
    'RIFIUTATA'
}

export class Trasferta implements BaseEntity {
    constructor(
        public id?: number,
        public codice?: string,
        public descrizione?: string,
        public dataInizio?: any,
        public dataFine?: any,
        public statoTrasferta?: Stato,
        public note?: any,
        public sedeId?: number,
        public dipendenteId?: number,
        public respondabileId?: number,
        public rifCommessaId?: number,
        public commessaId?: number,
    ) {
    }
}
