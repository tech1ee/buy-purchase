package dev.techie.buy_purchases.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.techie.buy_purchases.BuildConfig
import dev.techie.buy_purchases.data.database.PurchasesDatabase
import dev.techie.buy_purchases.data.api.AuthInterceptor
import dev.techie.buy_purchases.data.api.CurrenciesApi
import dev.techie.buy_purchases.data.database.CurrenciesDatabase
import dev.techie.buy_purchases.data.database.SettingsDatabase
import dev.techie.buy_purchases.data.repository.CurrenciesRepositoryImpl
import dev.techie.buy_purchases.data.repository.PurchasesRepositoryImpl
import dev.techie.buy_purchases.data.repository.SettingsRepositoryImpl
import dev.techie.buy_purchases.domain.CurrenciesRepository
import dev.techie.buy_purchases.domain.PurchasesRepository
import dev.techie.buy_purchases.domain.SettingsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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

    @Provides
    @Singleton
    fun provideCurrenciesRepository(currenciesDb: CurrenciesDatabase, api: CurrenciesApi): CurrenciesRepository {
        return CurrenciesRepositoryImpl(currenciesDb.currenciesDao(), api)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(db: SettingsDatabase): SettingsRepository {
        return SettingsRepositoryImpl(db.settingsDao())
    }

    @Provides
    @Singleton
    fun provideCurrenciesApi(okHttpClient: OkHttpClient): CurrenciesApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.apilayer.com/exchangerates_data/")
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .client(okHttpClient)
            .build()
        return retrofit.create(CurrenciesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrenciesDatabase(app: Application): CurrenciesDatabase {
        return Room.databaseBuilder(
            app,
            CurrenciesDatabase::class.java,
            CurrenciesDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideSettingsDatabase(app: Application): SettingsDatabase {
        return Room.databaseBuilder(
            app,
            SettingsDatabase::class.java,
            SettingsDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor(AuthInterceptor())

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    }
}