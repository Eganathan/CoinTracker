package online.onenut.cointracker.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

//@InstallIn(SingletonComponent::class)
//@Module
//object CoroutinesDispatchersModule {
//
//    @DefaultDispatcher
//    @Provides
//    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
//
//    @IoDispatcher
//    @Provides
//    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
//
//    @MainDispatcher
//    @Provides
//    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
//
//    @MainImmediateDispatcher
//    @Provides
//    fun providesMainImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
//
//    @Singleton
//    @ApplicationScope
//    @Provides
//    fun providesCoroutineScope(
//        @MainDispatcher mainDispatcher: CoroutineDispatcher,
//    ): CoroutineScope = CoroutineScope(SupervisorJob() + mainDispatcher)
//}