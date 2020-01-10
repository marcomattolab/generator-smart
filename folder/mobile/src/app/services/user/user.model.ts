import {BaseEntity} from '../../../model/base-entity';

export const enum UserRole {
  ROLE_ADMIN = 'ROLE_ADMIN',
  ROLE_USER = 'ROLE_USER',
  ROLE_OFFICER = 'ROLE_OFFICER',
  ROLE_PM = 'ROLE_PM'
}

export const enum Profilo {
  'Employee',
  'AccountingOfficer',
  'ProjectManager',
  'Administrator'
}

export class User implements BaseEntity {
    constructor(
        public id?: any,
        public login?: string,
        public firstName?: string,
        public lastName?: string,
        public email?: string,
        public activated?: boolean,
        public langKey?: string,
        public authorities?: UserRole[],
        public createdBy?: string,
        public createdDate?: Date,
        public lastModifiedBy?: string,
        public lastModifiedDate?: Date,
        public password?: string,
        public nome?: string,
        public cognome?: string,
        public dataNascita?: string,
        public codiceFiscale?: string,
        public cid?: string,
        public sede?: string,
        public profilo?: Profilo,
        public telefono?: string,
    ) {
        this.id = id ? id : null;
        this.login = login ? login : null;
        this.firstName = firstName ? firstName : null;
        this.lastName = lastName ? lastName : null;
        this.email = email ? email : null;
        this.activated = activated ? activated : false;
        this.langKey = langKey ? langKey : null;
        this.authorities = authorities ? authorities : null;
        this.createdBy = createdBy ? createdBy : null;
        this.createdDate = createdDate ? createdDate : null;
        this.lastModifiedBy = lastModifiedBy ? lastModifiedBy : null;
        this.lastModifiedDate = lastModifiedDate ? lastModifiedDate : null;
        this.password = password ? password : null;
    }
}
