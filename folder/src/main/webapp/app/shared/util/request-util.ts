import { HttpParams } from '@angular/common/http';

export const createRequestOption = (req?: any): HttpParams => {
    let options: HttpParams = new HttpParams();
    if (req) {
        Object.keys(req).forEach(key => {
            if (key !== 'sort') {
            		if (req[key].length > 0 || (req[key] !== null && req[key] !== '') || key === 'page' ||  key === 'size') {
                    options = options.set(key, req[key]);
                 }
            }
        });
        if (req.sort) {
            req.sort.forEach(val => {
                options = options.append('sort', val);
            });
        }
    }
    return options;
};

export const checkAndCompileSearchFilterEquals = (formControls, searchFilter, field: string) => {
    const value = formControls[field].value;
    console.log(value);
    if (value !== '') {
        searchFilter[`${field}.equals`] = value;
    }
    return searchFilter;
};

export const checkAndCompileSearchFilterContains = (formControls, searchFilter, field: string) => {
    const value = formControls[field].value;

    if (value !== '') {
        searchFilter[`${field}.contains`] = value;
    }
    return searchFilter;
};

export const checkAndCompileSearchBetween = (formControls, searchFilter, fieldDa: string, fieldA: string, fieldName: string) => {
    const valueDa = formControls[fieldDa].value;
    const valueA = formControls[fieldA].value;
    if (valueDa !== '') {
        searchFilter[`${fieldName}.greaterOrEqualThan`] = valueDa;
    }
    if (valueA !== '') {
        searchFilter[`${fieldName}.lessOrEqualThan`] = valueA;
    }
    return searchFilter;
};
