package com.xu.base

import android.app.Activity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import java.util.*

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/5/13 16:43
 * @version 2.2
 */
object AppManager : DefaultLifecycleObserver {

    private val mActivities = Stack<Activity>()

    private fun addActivity(activity: Activity) {
        mActivities.add(activity)
    }

    private fun removeActivity(activity: Activity) {
        mActivities.remove(activity)
    }

    fun removeAll() {
        mActivities.forEach {
            it.finish()
        }
        mActivities.clear()
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        addActivity(owner as Activity)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        removeActivity(owner as Activity)
    }
}