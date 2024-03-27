package com.houlis.haris.picfind.feature.details.navigation

import com.houlis.haris.picfind.ui.common.navigation.PicIdArg
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NavigationModule {

    @PicIdArg
    @Provides
    fun providePicIdArg() = PIC_ID_ARG
}
