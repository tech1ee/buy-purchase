package dev.techie.buy_purchases.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.techie.buy_purchases.entity.Purchase
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchasesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg purchases: Purchase)

    @Query("SELECT * FROM purchase")
    fun getAllPurchases(): Flow<List<Purchase>>

    @Query("SELECT * FROM purchase WHERE id = :id")
    suspend fun getPurchase(id: Int): Purchase?
}