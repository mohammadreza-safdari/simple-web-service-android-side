package com.example.presentation.registerScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow

@Composable
fun RegisterScreen(
    state: RegisterState,
    effect: Flow<RegisterEffect>,
    onEvent: (RegisterEvent) -> Unit,
    navigateToSportsScreen: () -> Unit,
    navigateToLoginScreen: () -> Unit
) {
    LaunchedEffect(Unit) {
        effect.collect(collector = {
             navigateToSportsScreen()
        })
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp)
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    "Create Account",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(Modifier.height(24.dp))

                OutlinedTextField(
                    value = state.name,
                    onValueChange = {
                        onEvent(RegisterEvent.NameChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Name") }
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.email,
                    onValueChange = {
                        onEvent(RegisterEvent.EmailChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email") }
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.password,
                    onValueChange = {
                        onEvent(RegisterEvent.PasswordChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(Modifier.height(12.dp))

                OutlinedTextField(
                    value = state.passwordConfirmation,
                    onValueChange = {
                        onEvent(RegisterEvent.PasswordConfirmationChanged(it))
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        onEvent(RegisterEvent.RegisterClicked)
                    },
                    enabled = !state.isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                ) {

                    if (state.isLoading)
                        CircularProgressIndicator(
                            modifier = Modifier.size(22.dp),
                            strokeWidth = 2.dp
                        )
                    else
                        Text("Register")
                }

                Spacer(Modifier.height(8.dp))

                TextButton(
                    onClick = {
                        navigateToLoginScreen()
                    }
                ) {
                    Text("Already have an account?")
                }

                Spacer(Modifier.height(8.dp))

                if(state.error != null) {
                    Text(state.error)
                }
            }
        }
    }
}