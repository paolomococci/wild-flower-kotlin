/**
 *
 * Copyright 2019 paolo mococci
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package local.example.wildflower

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText

import local.example.wildflower.R.id.main_edit_text
import local.example.wildflower.R.id.message_text_from_reply
import local.example.wildflower.R.id.reply_button
import local.example.wildflower.R.id.reply_edit_text
import local.example.wildflower.R.id.send_button
import local.example.wildflower.R.id.text_message

@LargeTest
@RunWith(AndroidJUnit4::class)
class TypedInstrumentedTests {

    /* remember to switch on the screen on testing device */

    private var toBeTypedMain: String? = null
    private var toBeTypedReply: String? = null

    @Rule
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Rule
    var replyActivityTestRule = ActivityTestRule(ReplyActivity::class.java)

    @Before
    fun setUp() {
        toBeTypedMain = "Welcome to Eden."
        toBeTypedReply = "Welcome to my world!"
        mainActivityTestRule.activity.supportFragmentManager.beginTransaction()
    }

    @Test
    fun clearedAndTypedTest() {
        onView(withId(main_edit_text)).check(matches(isDisplayed()))
        onView(withId(main_edit_text)).perform(
            clearText(),
            typeText("Welcome."), closeSoftKeyboard()
        )
    }

    @Test
    fun typedAndVerifyMessageTest() {
        onView(withId(main_edit_text)).perform(
            clearText(), typeText(toBeTypedMain!!),
            closeSoftKeyboard()
        )
        onView(withId(send_button)).perform(click())
        onView(withId(text_message)).check(matches(withText(toBeTypedMain)))
    }

    @Test
    fun mainMessageAndReplyTest() {
        onView(withId(main_edit_text)).perform(
            clearText(), typeText(toBeTypedMain!!),
            closeSoftKeyboard()
        )
        onView(withId(send_button)).perform(click())
        onView(withId(text_message)).check(matches(withText(toBeTypedMain)))
        onView(withId(reply_edit_text)).perform(
            clearText(), typeText(toBeTypedReply!!),
            closeSoftKeyboard()
        )
        onView(withId(reply_button)).perform(click())
        onView(withId(message_text_from_reply)).check(matches(withText(toBeTypedReply)))
    }
}
