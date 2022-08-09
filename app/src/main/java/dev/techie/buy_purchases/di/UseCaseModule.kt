package dev.techie.buy_purchases.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.techie.buy_purchases.domain.PurchasesRepository
import dev.techie.buy_purchases.domain.usecase.GetPurchase
import dev.techie.buy_purchases.domain.usecase.GetPurchases
import dev.techie.buy_purchases.domain.usecase.UpdatePurchase

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPurchasesUseCase(repository: PurchasesRepository): GetPurchases {
        return GetPurchases(repository)
    }

    @Provides
    fun provideGetPurchaseUseCase(repository: PurchasesRepository): GetPurchase {
        return GetPurchase(repository)
    }

    @Provides
    fun provideUpdatePurchaseUseCase(repository: PurchasesRepository): UpdatePurchase {
        return UpdatePurchase(repository)
    }
}