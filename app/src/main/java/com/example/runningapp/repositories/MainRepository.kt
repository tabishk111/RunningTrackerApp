package com.example.runningapp.repositories

import com.example.runningapp.db.Run
import com.example.runningapp.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)

    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunSortedByDate() = runDAO.getAllRunsSortedByDate()

    fun getAllRunSortedByDistance() = runDAO.getAllRunsSortedByDistance()

    fun getAllRunSortedByTimeInMillis() = runDAO.getTotalTimeInMillis()

    fun getAllRunSortedByAvgSpeed() = runDAO.getAllRunsSortedByAverageSpeed()

    fun getAllRunSortedByCaloriesBurned() = runDAO.getAllRunsSortedByCaloriesBurnt()

    fun getTotalAvgSpeed() = runDAO.getTotalAverageSpeed()

    fun getTotalDistance() = runDAO.getTotalDistance()

    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurnt()

    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()
}