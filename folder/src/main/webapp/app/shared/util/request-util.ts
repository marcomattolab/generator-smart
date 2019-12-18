import { HttpParams } from '@angular/common/http';

export const createRequestOption = (req?: any): HttpParams => {
    let options: HttpParams = new HttpParams();
    if (req) {
        Object.keys(req).forEach(key => {
            if (key !== 'sort') {
               if (req[key].length > 0 || key === 'page' ||  key === 'size') {
                   // console.log('## TEST - Filter key: ' + key + '  req[key]: ' + req[key]);
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
