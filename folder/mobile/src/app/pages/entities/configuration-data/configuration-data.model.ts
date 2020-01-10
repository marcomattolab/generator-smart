import { BaseEntity } from 'src/model/base-entity';

export const enum Configtype {
    'CORE',
    'MAIL',
    'SOGLIE',
    'FEEDBACK'
}

export class ConfigurationData implements BaseEntity {
    constructor(
        public id?: number,
        public key?: string,
        public type?: Configtype,
        public value?: string,
        public isActive?: boolean,
    ) {
        this.isActive = false;
    }
}
