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

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private var editText: EditText? = null
    private var replyHeadTextView: TextView? = null
    private var replyMessageTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.main_edit_text)
        replyHeadTextView = findViewById(R.id.header_text_from_reply)
        replyMessageTextView = findViewById(R.id.message_text_from_reply)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                val reply = data!!.getStringExtra(ReplyActivity.EXTRA_REPLY)
                replyHeadTextView!!.visibility = View.VISIBLE
                replyMessageTextView!!.text = reply
                replyMessageTextView!!.visibility = View.VISIBLE
            }
        }
    }

    fun sendToSecondActivity(view: View) {
        Log.d(LOG_TAG, "Send button clicked!")
        val intent = Intent(this, ReplyActivity::class.java)
        val message = editText!!.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)
        startActivityForResult(intent, TEXT_REQUEST)
    }

    companion object {

        private val LOG_TAG = MainActivity::class.java.simpleName
        const val EXTRA_MESSAGE = "local.example.sunflower.extra.MESSAGE"
        const val TEXT_REQUEST = 1
    }
}
