package com.sanyasdada.errorhandlingincompose.presentation

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FormUI() {

    var firstName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var mobileCountryCode by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var dateOfBirth by remember { mutableStateOf("") }
    var mobileCountry by remember { mutableStateOf<Country?>(null) }
    val context = LocalContext.current




    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*OutlinedTextField(
            value = firstNam

            placeholder = "First Name",
            textAlign = TextAlign.Center,
            keyboardType = KeyboardType.Text,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Gray,
            )

        )*/
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text(text = "First Name") },
            placeholder = { Text(text = "First Name") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray,
                placeholderColor = Color.DarkGray,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Red


            ),
            leadingIcon = { Icon(imageVector = Icons.Filled.Person, contentDescription = "null") },
            maxLines = 1

        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            placeholder = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray,
                placeholderColor = Color.DarkGray,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Red
            ),
            leadingIcon = {
                IconButton(onClick = {
                    isPasswordVisible = !isPasswordVisible
                }) {
                    Icon(
                        imageVector = if (isPasswordVisible)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff,
                        contentDescription = "Password Visibility"
                    )
                }
            },
            visualTransformation = if (isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            maxLines = 1
        )
        OutlinedTextField(

            value =mobileNumber ,
            onValueChange = { mobileNumber = it },
            label = { Text(text = "Mobile Number") },
            placeholder = { Text(text = "Mobile Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray,
                placeholderColor = Color.DarkGray,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Red
            ),
            leadingIcon = {
                mobileCountry?.let {
                    CountryPickerView(
                        countries = getCountriesList(),
                        selectedCountry = it,
                        onSelection = {selectedCountry ->
                            mobileCountry = selectedCountry
                        }
                    )
                }
            },
            maxLines = 1
        )

        OutlinedTextField(
            modifier = Modifier.clickable {

                showDatePickerDialog(context, dateOfBirth)
            },
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            label = { Text(text = "Date of Birth") },
            placeholder = { Text(text = "Date of Birth") },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.DarkGray,
                placeholderColor = Color.DarkGray,
                cursorColor = Color.Black,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Red
            ),
            enabled = false




        )

    }
}



private  var dateFormat = "yyyy-MM-dd"
fun showDatePickerDialog(context: Context, dateOfBirth: String) {
    var dateFormat = "yyyy-MM-dd"
    val calendar = getCalendar(dateOfBirth)
    DatePickerDialog(
        context,
        { _, year, month, day ->
          var  dateOfBirth = getPickedDateAsString(year, month, day)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()

}

fun getPickedDateAsString(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val dateFormat = SimpleDateFormat(dateFormat)
    return dateFormat.format(calendar.time)
}

fun getCalendar(dateOfBirth: String): Calendar {
    return if (dateOfBirth.isEmpty())
        Calendar.getInstance()
    else
        getLastPickedDateCalendar(dateOfBirth)

}

fun getLastPickedDateCalendar(dateOfBirth: String): Calendar {
    val dateFormat = SimpleDateFormat(dateFormat)
    val calendar = Calendar.getInstance()
    calendar.time = dateFormat.parse(dateOfBirth)
    return calendar
}

@Preview
@Composable
fun showMe() {
    FormUI()
}