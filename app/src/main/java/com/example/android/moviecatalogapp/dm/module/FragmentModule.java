package com.example.android.moviecatalogapp.dm.module;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 11/5/2017.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }

    @Provides
    Fragment provideFragment(){
        return fragment;
    }
}
