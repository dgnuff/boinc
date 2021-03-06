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

import com.google.common.testing.EqualsTester
import org.junit.Test

class TimePreferencesTest {
    @Test
    fun `Test equals() and hashCode()`() {
        val weekPrefs = arrayOf(TimeSpan(), null, null, null, null, null, null)
        val timePreferences2 = TimePreferences(weekPrefs = weekPrefs)

        EqualsTester().addEqualityGroup(TimePreferences(), TimePreferences())
                .addEqualityGroup(TimePreferences(1.0))
                .addEqualityGroup(TimePreferences(endHour = 1.0))
                .addEqualityGroup(timePreferences2)
                .testEquals()
    }
}
