package com.ychen.tourism.helper;

import android.net.Uri;

/**
 * Created by evast on 16-3-22.
 */
public class Table {
    public static final String ID = "_id";
    public static final String AUTHORITY = "com.ychen.tourism.helper.ProviderHelper";
    public static final String PLAN_TABLE_NAME = "plan_table";
    public static class PlanTable extends Table{
        public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/" + PLAN_TABLE_NAME);
        public static final String NAME = "name";
        public static final String INFO = "info";
        public static final String START_TIME = "start_time";
        public static final String END_TIME = "end_time";
        public static final String ADDRESS = "address";
        public static final String OVERHEAD = "overhead";
    }
}
