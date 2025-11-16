package com.example.myfirstandroidapp.ui.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.myfirstandroidapp.data.model.UserDto
import com.example.myfirstandroidapp.ui.theme.MyFirstAndroidAppTheme

@Composable
fun UserCard (user: UserDto, modifier: Modifier = Modifier){
    Card ( modifier = modifier.fillMaxHeight()) {
        Row(Modifier.padding(12.dp)){
            AsyncImage(
                model = user.avatar,
                contentDescription = "Avatar",
                modifier = Modifier.size(56.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column (Modifier.weight(1f)) {
                Text(
                    user.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    user.email,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    user.role,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }


}

@Preview(showBackground = true, name = "UserCard")
@Composable
private fun UserCardPreview() {
    MyFirstAndroidAppTheme {
        UserCard(
            user = UserDto(
                id = 1,
                email = "aaa@mail.com",
                name = "John DOE",
                role = "customer",
                avatar = "https://i.imgur.com/LDOO4Qs.jpg",
                creationAt = "2025-11-03T17:19:40.000Z",
                updatedAt = "2025-11-03T17:19:40.000Z"
            )
        )
    }
}