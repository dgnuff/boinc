/*
 * This file is part of BOINC.
 * http://boinc.berkeley.edu
 * Copyright (C) 2020 University of California
 *
 * BOINC is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * BOINC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with BOINC.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.berkeley.boinc.rpc

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import edu.berkeley.boinc.rpc.App
import edu.berkeley.boinc.rpc.AppVersion
import edu.berkeley.boinc.rpc.Project
import edu.berkeley.boinc.rpc.WorkUnit

data class Result(
        var name: String = "",
        var workUnitName: String = "",
        var projectURL: String = "",
        var versionNum: Int = 0,
        var planClass: String? = null,
        var reportDeadline: Long = 0,
        var receivedTime: Long = 0,
        var finalCPUTime: Double = 0.0,
        var finalElapsedTime: Double = 0.0,
        var state: Int = 0,
        var schedulerState: Int = 0,
        var exitStatus: Int = 0,
        var signal: Int = 0,
        var stderrOut: String? = null,
        var activeTaskState: Int = 0,
        var appVersionNum: Int = 0,
        var slot: Int = -1,
        var pid: Int = 0,
        var checkpointCPUTime: Double = 0.0,
        var currentCPUTime: Double = 0.0,
        var fractionDone: Float = 0f,
        var elapsedTime: Double = 0.0,
        var swapSize: Double = 0.0,
        var workingSetSizeSmoothed: Double = 0.0,
        /**
         * actually, estimated elapsed time remaining
         */
        var estimatedCPUTimeRemaining: Double = 0.0,
        var graphicsModeAcked: Int = 0,
        var graphicsExecPath: String? = null,
        /**
         * only present if graphics_exec_path is
         */
        var slotPath: String? = null,
        var resources: String? = null,
        var project: Project? = null,
        var appVersion: AppVersion? = null,
        var app: App? = null,
        var workUnit: WorkUnit? = null,
        var isReadyToReport: Boolean = false,
        var gotServerAck: Boolean = false,
        var isSuspendedViaGUI: Boolean = false,
        var isProjectSuspendedViaGUI: Boolean = false,
        var isCoprocMissing: Boolean = false,
        var gpuMemWait: Boolean = false,
        var isActiveTask: Boolean = false,
        var supportsGraphics: Boolean = false,
        var isTooLarge: Boolean = false,
        var needsShmem: Boolean = false,
        var isEdfScheduled: Boolean = false
) : Parcelable {
    private constructor(parcel: Parcel) : this(parcel.readString() ?: "", parcel.readString() ?: "",
            parcel.readString() ?: "", parcel.readInt(), parcel.readString(), parcel.readLong(),
            parcel.readLong(), parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readInt(),
            parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(),
            parcel.readInt(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readFloat(),
            parcel.readDouble(), parcel.readDouble(), parcel.readDouble(), parcel.readDouble(),
            parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(),
            parcel.readValue(Project::class.java.classLoader) as Project?,
            parcel.readValue(AppVersion::class.java.classLoader) as AppVersion?,
            parcel.readValue(App::class.java.classLoader) as App?,
            parcel.readValue(WorkUnit::class.java.classLoader) as WorkUnit?) {
        val bArray = parcel.createBooleanArray()!!
        isReadyToReport = bArray[0]
        gotServerAck = bArray[1]
        isSuspendedViaGUI = bArray[2]
        isProjectSuspendedViaGUI = bArray[3]
        isCoprocMissing = bArray[4]
        gpuMemWait = bArray[5]
        isActiveTask = bArray[6]
        supportsGraphics = bArray[7]
        isTooLarge = bArray[8]
        needsShmem = bArray[9]
        isEdfScheduled = bArray[10]
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(name)
        dest.writeString(workUnitName)
        dest.writeString(projectURL)
        dest.writeInt(versionNum)
        dest.writeString(planClass)
        dest.writeLong(reportDeadline)
        dest.writeLong(receivedTime)
        dest.writeDouble(finalCPUTime)
        dest.writeDouble(finalElapsedTime)
        dest.writeInt(state)
        dest.writeInt(schedulerState)
        dest.writeInt(exitStatus)
        dest.writeInt(signal)
        dest.writeString(stderrOut)
        dest.writeInt(activeTaskState)
        dest.writeInt(appVersionNum)
        dest.writeInt(slot)
        dest.writeInt(pid)
        dest.writeDouble(checkpointCPUTime)
        dest.writeDouble(currentCPUTime)
        dest.writeFloat(fractionDone)
        dest.writeDouble(elapsedTime)
        dest.writeDouble(swapSize)
        dest.writeDouble(workingSetSizeSmoothed)
        dest.writeDouble(estimatedCPUTimeRemaining)
        dest.writeInt(graphicsModeAcked)
        dest.writeString(graphicsExecPath)
        dest.writeString(slotPath)
        dest.writeString(resources)
        dest.writeValue(project)
        dest.writeValue(appVersion)
        dest.writeValue(app)
        dest.writeValue(workUnit)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            val bArray: BooleanArray = dest.createBooleanArray()!!
            isReadyToReport = bArray[0]
            gotServerAck = bArray[1]
            isSuspendedViaGUI = bArray[2]
            isProjectSuspendedViaGUI = bArray[3]
            isCoprocMissing = bArray[4]
            gpuMemWait = bArray[5]
            isActiveTask = bArray[6]
            supportsGraphics = bArray[7]
            isTooLarge = bArray[8]
            needsShmem = bArray[9]
            isEdfScheduled = bArray[10]
        } else {
            isReadyToReport = dest.readBoolean()
            gotServerAck = dest.readBoolean()
            isSuspendedViaGUI = dest.readBoolean()
            isProjectSuspendedViaGUI = dest.readBoolean()
            isCoprocMissing = dest.readBoolean()
            gpuMemWait = dest.readBoolean()
            isActiveTask = dest.readBoolean()
            supportsGraphics = dest.readBoolean()
            isTooLarge = dest.readBoolean()
            needsShmem = dest.readBoolean()
            isEdfScheduled = dest.readBoolean()
        }
    }

    object Fields {
        const val WU_NAME = "wu_name"
        const val VERSION_NUM = "version_num"
        const val REPORT_DEADLINE = "report_deadline"
        const val RECEIVED_TIME = "received_time"
        const val READY_TO_REPORT = "ready_to_report"
        const val GOT_SERVER_ACK = "got_server_ack"
        const val FINAL_CPU_TIME = "final_cpu_time"
        const val FINAL_ELAPSED_TIME = "final_elapsed_time"
        const val STATE = "state"
        const val SCHEDULER_STATE = "scheduler_state"
        const val EXIT_STATUS = "exit_status"
        const val SUSPENDED_VIA_GUI = "suspended_via_gui"
        const val PROJECT_SUSPENDED_VIA_GUI = "project_suspended_via_gui"
        const val ACTIVE_TASK = "active_task"
        const val ACTIVE_TASK_STATE = "active_task_state"
        const val APP_VERSION_NUM = "app_version_num"
        const val SLOT = "slot"
        const val PID = "pid"
        const val CHECKPOINT_CPU_TIME = "checkpoint_cpu_time"
        const val CURRENT_CPU_TIME = "current_cpu_time"
        const val FRACTION_DONE = "fraction_done"
        const val ELAPSED_TIME = "elapsed_time"
        const val SWAP_SIZE = "swap_size"
        const val WORKING_SET_SIZE_SMOOTHED = "working_set_size_smoothed"
        const val ESTIMATED_CPU_TIME_REMAINING = "estimated_cpu_time_remaining"
        const val SUPPORTS_GRAPHICS = "supports_graphics"
        const val GRAPHICS_MODE_ACKED = "graphics_mode_acked"
        const val TOO_LARGE = "too_large"
        const val NEEDS_SHMEM = "needs_shmem"
        const val EDF_SCHEDULED = "edf_scheduled"
        const val GRAPHICS_EXEC_PATH = "graphics_exec_path"
        const val SLOT_PATH = "slot_path"
        const val RESOURCES = "resources"
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Result> = object : Parcelable.Creator<Result> {
            override fun createFromParcel(parcel: Parcel) = Result(parcel)

            override fun newArray(size: Int) = arrayOfNulls<Result>(size)
        }
    }
}
