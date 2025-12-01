package ca.gbc.treasurely.ui.user

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import ca.gbc.treasurely.data.model.User
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.lazy.items



@Composable
fun UserListScreen(
    viewModel: UserListViewModel,
    onEditUser: (String) -> Unit,
    onAddUser: () -> Unit
) {
    val users by viewModel.users.observeAsState(emptyList())
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddUser) {
                Text("+")
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(users) { user ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onEditUser(user.id) }
                ) {
                    Column(Modifier.padding(16.dp)) {

                        Text(
                            text = user.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(user.email)
                        Text(user.phone)

                        Spacer(Modifier.height(8.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // CALL
                            TextButton(onClick = {
                                val intent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:${user.phone}")
                                }
                                context.startActivity(intent)
                            }) { Text("Call") }

                            // SMS
                            TextButton(onClick = {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("smsto:${user.phone}")
                                }
                                context.startActivity(intent)
                            }) { Text("SMS") }

                            // EMAIL
                            TextButton(onClick = {
                                val intent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:${user.email}")
                                }
                                context.startActivity(intent)
                            }) { Text("Email") }

                            Spacer(Modifier.weight(1f))

                            // DELETE
                            IconButton(onClick = {
                                viewModel.deleteUser(user)
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Delete,
                                    contentDescription = "Delete"
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
