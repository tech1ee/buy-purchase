package dev.techie.buy_purchases.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.techie.buy_purchases.data.PurchasesDatabase
import dev.techie.buy_purchases.data.repository.PurchasesRepositoryImpl
import dev.techie.buy_purchases.domain.PurchasesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePurchasesDatabase(app: Application): PurchasesDatabase {
        return Room.databaseBuilder(
            app,
            PurchasesDatabase::class.java,
            PurchasesDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePurchasesRepository(db: PurchasesDatabase): PurchasesRepository {
        return PurchasesRepositoryImpl(db.purchasesDao())
    }
}