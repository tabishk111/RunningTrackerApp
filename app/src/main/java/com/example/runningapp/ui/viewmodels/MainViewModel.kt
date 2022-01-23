package com.example.runningapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.runningapp.db.Run
import com.example.runningapp.other.SortType
import com.example.runningapp.repositories.MainRepository
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    val mainRepository: MainRepository
) :ViewModel(){

    private val runSortedByDate = mainRepository.getAllRunSortedByDate()
    private val runSortedByDistance = mainRepository.getAllRunSortedByDistance()
    private val runSortedByCaloriesBurned = mainRepository.getAllRunSortedByCaloriesBurned()
    private val runSortedByTimeInMillis = mainRepository.getAllRunSortedByTimeInMillis()
    private val runSortedByAvgSpeed = mainRepository.getAllRunSortedByAvgSpeed()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init {
        runs.addSource(runSortedByDate){ result ->
            if(sortType == SortType.DATE){
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runSortedByAvgSpeed){ result ->
            if(sortType == SortType.AVERAGE_SPEED){
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runSortedByCaloriesBurned){ result ->
            if(sortType == SortType.CALORIES_BURNED){
                result?.let { runs.value = it }
            }
        }

        runs.addSource(runSortedByDistance){ result ->
            if(sortType == SortType.DISTANCE){
                result?.let { runs.value = it }
            }
        }

//        runs.addSource(runSortedByTimeInMillis){ result ->
//            if(sortType == SortType.DISTANCE){
//                result?.let { runs.value = it }
//            }
//        }

    }

    fun sortRuns(sortType: SortType) = when(sortType){
        SortType.DATE -> runSortedByDate.value?.let { runs.value = it }
        SortType.AVERAGE_SPEED -> runSortedByAvgSpeed.value?.let { runs.value = it }
        SortType.DISTANCE -> runSortedByDistance.value?.let { runs.value = it }
        //SortType.RUNNING_TIME -> runSortedByTimeInMillis.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runSortedByCaloriesBurned.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> TODO()
    }.also {
        this.sortType = sortType
    }

    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }
}