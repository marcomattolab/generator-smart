import { BaseEntity } from 'src/model/base-entity';

export const enum Profilo {
    'Employee',
    'AccountingOfficer',
    'ProjectManager',
    'Administrator'
}

export class Persona implements BaseEntity {
    constructor(
        public id?: number,
        public nome?: string,
        public cognome?: string,
        public dataNascita?: string,
        public codiceFiscale?: string,
        public cid?: string,
        public sede?: string,
        public profilo?: Profilo,
        public email?: string,
        public telefono?: string,
    ) {
    }
}
