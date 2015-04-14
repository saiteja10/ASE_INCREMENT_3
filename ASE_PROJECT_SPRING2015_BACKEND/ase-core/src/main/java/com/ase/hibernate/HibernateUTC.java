package com.ase.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Environment;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.*;
import java.util.Calendar;
import java.util.TimeZone;

public abstract class HibernateUTC implements UserType {

    /**
     * the SQL type this type manages
     */
    protected static int[] SQL_TYPES_UTC = {Types.TIMESTAMP};

    /**
     * @see net.sf.hibernate.UserType#sqlTypes()
     */
    public int[] sqlTypes() {
        return SQL_TYPES_UTC;
    }

    /**
     * @see net.sf.hibernate.UserType#equals(Object, Object)
     */
    public boolean equals(Object x, Object y) {
        return (x == null) ? (y == null) : x.equals(y);
    }

    /**
     * @see net.sf.hibernate.UserType#isMutable()
     */
    public boolean isMutable() {
        return true;
    }

    /**
     * @see net.sf.hibernate.UserType#returnedClass()
     */
    public Class<Date> returnedClass() {
        return objectClass;
    }

    /**
     * The class of objects returned by <code>nullSafeGet</code>. Currently,
     * returned objects are derived from this class, not exactly this class.
     */
    protected Class<Date> objectClass = Date.class;

    /**
     * Get a hashcode for the instance, consistent with persistence "equality"
     */
    public int hashCode(Object x) throws HibernateException {
        return x.hashCode();
    }

    /**
     * Transform the object into its cacheable representation. At the very least
     * this method should perform a deep copy if the type is mutable. That may
     * not be enough for some implementations, however; for example,
     * associations must be cached as identifier values. (optional operation)
     *
     * @param value the object to be cached
     * @return a cachable representation of the object
     * @throws org.hibernate.HibernateException
     *
     */
    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) deepCopy(value);
    }

    /**
     * Reconstruct an object from the cacheable representation. At the very
     * least this method should perform a deep copy if the type is mutable.
     * (optional operation)
     *
     * @param cached the object to be cached
     * @param owner  the owner of the cached object
     * @return a reconstructed object from the cachable representation
     * @throws org.hibernate.HibernateException
     *
     */
    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return deepCopy(cached);
    }

    /**
     * During merge, replace the existing (target) value in the entity we are
     * merging to with a new (original) value from the detached entity we are
     * merging. For immutable objects, or null values, it is safe to simply
     * return the first parameter. For mutable objects, it is safe to return a
     * copy of the first parameter. For objects with component values, it might
     * make sense to recursively replace component values.
     *
     * @param original the value from the detached entity being merged
     * @param target   the value in the managed entity
     * @return the value to be merged
     */
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return deepCopy(original);
    }

    /**
     * Like a Hibernate date, but using the UTC TimeZone (not the default
     * TimeZone).
     */
    public static class DateType extends HibernateUTC {
        protected static int[] SQL_TYPES_DATE = {Types.DATE};

        /**
         * @see net.sf.hibernate.UserType#sqlTypes()
         */
        public int[] sqlTypes() {
            return SQL_TYPES_DATE;
        }

        /**
         * @see net.sf.hibernate.UserType#deepCopy(Object)
         */
        public Object deepCopy(Object value) {
            return (value == null) ? null : new Date(((Date) value).getTime());

        }

        /**
         * @see net.sf.hibernate.UserType#nullSafeGet(java.sql.ResultSet,
         *      String[], Object)
         */
        public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws SQLException {
            return rs.getDate(names[0], sUTCCalendar);
        }

        /**
         * @see net.sf.hibernate.UserType#nullSafeSet(java.sql.PreparedStatement,
         *      Object, int)
         */
        public void nullSafeSet(PreparedStatement st, Object value, int index) throws SQLException {
            if (!(value instanceof Date))
                value = deepCopy(value);
            st.setDate(index, (Date) value, sUTCCalendar);
        }
    }

    /**
     * Like a Hibernate time, but using the UTC TimeZone (not the default
     * TimeZone).
     */
    public static class TimeType extends HibernateUTC {

        protected static int[] SQL_TYPES_TIME = {Types.TIME};

        /**
         * @see net.sf.hibernate.UserType#sqlTypes()
         */
        public int[] sqlTypes() {
            return SQL_TYPES_TIME;
        }

        /**
         * @see net.sf.hibernate.UserType#deepCopy(Object)
         */
        public Object deepCopy(Object value) {
            return (value == null) ? null : new Time(((Date) value).getTime());
        }

        /**
         * @see net.sf.hibernate.UserType#nullSafeGet(java.sql.ResultSet,
         *      String[], Object)
         */
        public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws SQLException {
            return rs.getTime(names[0], sUTCCalendar);
        }

        /**
         * @see net.sf.hibernate.UserType#nullSafeSet(java.sql.PreparedStatement,
         *      Object, int)
         */
        public void nullSafeSet(PreparedStatement st, Object value, int index) throws SQLException {
            if (!(value instanceof Time))
                value = deepCopy(value);
            st.setTime(index, (Time) value, sUTCCalendar);
        }
    }

    /**
     * Like a Hibernate timestamp, but using the UTC TimeZone (not the default
     * TimeZone).
     */
    public static class TimestampType extends HibernateUTC {

        /**
         * @author siddhartha. Editing this to make it usable with
         * java.util.Date.
         * @see net.sf.hibernate.UserType#deepCopy(Object)
         */
        public Object deepCopy(Object value) {
            if (null == value)
                return null;
            java.util.Date utilDate = (java.util.Date) value;
            return new Timestamp(utilDate.getTime());

        }

        /**
         * @see net.sf.hibernate.UserType#nullSafeGet(java.sql.ResultSet,
         *      String[], Object)
         */
        public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws SQLException {
            return rs.getTimestamp(names[0]);
        }

        /**
         * @see net.sf.hibernate.UserType#nullSafeSet(java.sql.PreparedStatement,
         *      Object, int)
         */

        public void nullSafeSet(PreparedStatement st, Object value, int index) throws SQLException {
            if (!(value instanceof Timestamp))
                value = deepCopy(value);
            st.setTimestamp(index, (Timestamp) value);
        }

        public boolean equals(Object x, Object y) {
            if ((x == null) && (y == null)) {
                return true;
            }
            if (x instanceof java.util.Date && y instanceof java.util.Date) {
                java.util.Date newX = (java.util.Date) x;
                java.util.Date newY = (java.util.Date) y;
                return newX.compareTo(newY) == 0;
            }
            return false;
        }

    }

    public static class CalendarType extends HibernateUTC {

        public Class<Calendar> getReturnedClass() {
            return Calendar.class;
        }

        /**
         * @see net.sf.hibernate.UserType#deepCopy(Object)
         */
        public Object deepCopy(Object value) {
            if (value == null) {
                return null;
            }
            Calendar c = (Calendar) sUTCCalendar.clone();
            c.setTimeInMillis(((Calendar) value).getTimeInMillis());
            return c;
        }

        /**
         * @see net.sf.hibernate.UserType#nullSafeGet(java.sql.ResultSet,
         *      String[], Object)
         */
        public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws SQLException {
            Timestamp ts = rs.getTimestamp(names[0], sUTCCalendar);
            if (ts == null || rs.wasNull()) {
                return null;
            }
            Calendar cal = (Calendar) sUTCCalendar.clone();
            if (Environment.jvmHasTimestampBug()) {
                cal.setTime(new Date(ts.getTime() + ts.getNanos() / 1000000));
            } else {
                cal.setTime(ts);
            }
            return cal;

        }

        /**
         * @see net.sf.hibernate.UserType#nullSafeSet(java.sql.PreparedStatement,
         *      Object, int)
         */

        public void nullSafeSet(PreparedStatement st, Object value, int index) throws SQLException {
            if (value == null) {
                st.setNull(index, Types.TIMESTAMP);
            } else {
                Timestamp t = new Timestamp(((Calendar) value).getTimeInMillis());
                st.setTimestamp(index, t, sUTCCalendar);
            }
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.hibernate.usertype.UserType#equals(java.lang.Object,
         * java.lang.Object)
         */
        public boolean equals(Object x, Object y) {
            if (x == y)
                return true;
            if (x == null || y == null)
                return false;

            Calendar calendar1 = (Calendar) x;
            Calendar calendar2 = (Calendar) y;

            return calendar1.getTimeInMillis() == calendar2.getTimeInMillis();
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
         */
        public int hashCode(Object x) throws HibernateException {
            return new Long(((Calendar) x).getTimeInMillis()).hashCode();
        }
    }

    /**
     * Note 071107: passing the static sUTCCalendar instance to the
     * setTimestamp(), getTimestamp() calls above has concurrency issues, as
     * some JDBC drivers do modify the supplied calendar instance. More
     * defensive code should create a new Calendar instance in UTC and pass it
     * to each getTimestamp() / setTimestamp() call.
     *
     */

    /**
     * the Calendar to hold the UTC timezone
     */
    private static Calendar sUTCCalendar = Calendar.getInstance();

    static {
        // set the timezone for the calendar to UTC (= GMT)
        sUTCCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
}
