import { BaseEntity } from 'src/model/base-entity';

export class Giustificativo implements BaseEntity {
    constructor(
        public id?: number,
        public allegatoContentType?: string,
        public allegato?: any,
        public spesaId?: number,
    ) {
    }
}
