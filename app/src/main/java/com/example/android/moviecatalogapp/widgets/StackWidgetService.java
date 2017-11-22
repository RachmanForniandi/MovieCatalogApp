package com.example.android.moviecatalogapp.widgets;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by Lenovo on 11/21/2017.
 */

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(
                this.getApplicationContext(),
                intent
        );
    }
}
