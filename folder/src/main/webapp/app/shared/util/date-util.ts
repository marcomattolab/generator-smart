import * as moment from 'moment';
import { DATE_TIME_FORMAT_INSTANT } from 'app/shared/constants/input.constants';

export const toTimestamp = (date: moment.Moment, hours: number, minutes: number): moment.Moment => {
    const datetime = date.hours(hours).minutes(minutes);
    /*
    console.log('moment', datetime);
    console.log('momentToJson', datetime.toJSON());
    console.log('momentFormatInstant', datetime.format(DATE_TIME_FORMAT_INSTANT)+'Z');
    console.log('JSON.stringify', JSON.stringify(datetime));
    */
    return datetime;
};

export const toTimestampInizio = (date: moment.Moment): moment.Moment => {
    return toTimestamp(date, 0, 0);
};

export const toTimestampFine = (date: moment.Moment): moment.Moment => {
    return toTimestamp(date, 23, 59);
};
