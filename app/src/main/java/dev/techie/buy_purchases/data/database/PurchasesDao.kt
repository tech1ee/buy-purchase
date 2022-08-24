package dev.techie.buy_purchases.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.techie.buy_purchases.data.database.entity.PurchaseDb
import kotlinx.coroutines.flow.Flow

@Dao
interface PurchasesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg purchases: PurchaseDb)

    @Query("SELECT * FROM purchasedb")
    fun getAllPurchases(): Flow<List<PurchaseDb>>

    @Query("SELECT * FROM purchasedb WHERE id = :id")
    suspend fun getPurchase(id: Int): PurchaseDb?

    @Query("DELETE FROM purchasedb WHERE id = :id")
    suspend fun delete(id: Int)
}