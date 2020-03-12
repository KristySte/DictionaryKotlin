package com.madonasyombua.dictionarykotlin.data.room.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.madonasyombua.dictionarykotlin.data.room.entity.WordEntity

/**
 * @author madona
 * The data accessible object one of room annotations (DAO class). In this case
 * the DAO provides an api for reading and writing data.
 * We can perform CRUD(create, Read, Update, Delete)
 * A class implements an interface thereby inheriting the abstract methods of the interface
 *
 */
@Dao
interface WordDao {


    @Query("SELECT * FROM word")
    fun getWordList(): List<WordEntity>

    @Query("SELECT * FROM word WHERE word.defId==:defId")
    fun getWordByDefId(defId: Int): WordEntity?

    @Query("DELETE FROM word WHERE word.defId==:id")
    fun deleteById(id: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWord(wordEntity: WordEntity)

    @Update(onConflict = REPLACE)
    fun updateWord(wordEntity: WordEntity): Int

    @Delete
    fun delete(wordEntity: WordEntity)

}