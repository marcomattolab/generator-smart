import { BaseEntity } from 'src/model/base-entity';
import { Trasferta } from '../trasferta/trasferta.model';

export class Commessa implements BaseEntity {
    constructor(
        public id?: number,
        public codiceCommessa?: string,
        public nomeProgetto?: string,
        public dataInizio?: any,
        public dataFine?: any,
        public note?: any,
        public isActive?: boolean,
        public trasfertaRifs?: Trasferta[],
    ) {
        this.isActive = false;
    }
}
