package dev.techie.buy_purchases.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.techie.buy_purchases.domain.CurrenciesRepository
import dev.techie.buy_purchases.domain.PurchasesRepository
import dev.techie.buy_purchases.domain.usecase.*

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetPurchasesUseCase(repository: PurchasesRepository): GetPurchases {
        return GetPurchases(repository)
    }

    @Provides
    fun provideGetPurchaseUseCase(repository: PurchasesRepository): GetPurchase {
        return GetPurchase(repository)
    }

    @Provides
    fun provideDeletePurchaseUseCase(repository: PurchasesRepository): DeletePurchase {
        return DeletePurchase(repository)
    }

    @Provides
    fun provideUpdatePurchaseUseCase(repository: PurchasesRepository): UpdatePurchase {
        return UpdatePurchase(repository)
    }

    @Provides
    fun provideGetCurrencySymbols(repository: CurrenciesRepository): GetAllCurrencySymbols {
        return GetAllCurrencySymbols(repository)
    }
}