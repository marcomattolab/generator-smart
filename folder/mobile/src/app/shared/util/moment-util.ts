import * as moment from 'moment';
import {Moment} from 'moment';

/**
 * method that returns a Moment date object ignoring the time zone influence.
 * @param dateStr
 */
export function getMomentDateNoTZ(dateStr: string): Moment {
  let momentDate = dateStr ? moment.utc(dateStr).local(false) : null;

  // workaround in order to keep the same day coming from 'dateStr', without changes from the time offset
  // ie: before it changed like this: 2015-10-10T00:00:00+01:00 => 2015-10-09 , because of 1 hour utc subtraction
  if (momentDate) {
    momentDate = momentDate.add(momentDate.utcOffset(), 'minutes');
  }

  return momentDate;
}
