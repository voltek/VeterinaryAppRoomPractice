package com.example.roompracticeapp.repository.veterinary.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roompracticeapp.repository.RepositoryConstants.CN_PET_AGE
import com.example.roompracticeapp.repository.RepositoryConstants.CN_PET_NAME
import com.example.roompracticeapp.repository.RepositoryConstants.CN_PET_RACE
import com.example.roompracticeapp.repository.RepositoryConstants.PET_TABLE_NAME

@Entity(tableName = PET_TABLE_NAME)
data class PetEntity(
    @ColumnInfo(name = CN_PET_NAME) val name: String?,
    @ColumnInfo(name = CN_PET_RACE) val race: String?,
    @ColumnInfo(name = CN_PET_AGE) val age: Int?,
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
)