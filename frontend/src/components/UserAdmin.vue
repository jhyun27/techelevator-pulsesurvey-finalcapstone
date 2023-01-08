<template>
  <div id="app">
    <v-app id="inspire">
      <v-data-table :headers="user" :items="users" class="elevation-1">
        <template v-slot:top>
          <v-toolbar flat color="white">
            <v-toolbar-title>Admin User Homepage</v-toolbar-title>
            <v-divider class="mx-4" inset vertical></v-divider>
            <v-spacer></v-spacer>

            <!-- Add user card -->
            <v-dialog v-model="dialogAdd" max-width="500px">
              <template v-slot:activator="{ on }">
                <v-btn color="primary" dark class="mb-2" v-on="on">Add user</v-btn>
              </template>

              <v-card>
                <v-card-title>
                  <span class="headline">Add User</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field v-model="newItem.username" hint="Username" persistent-hint></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-select
                          hint="Role"
                          persistent-hint
                          :items="roleOptions"
                          solo
                          v-model="newItem.role"
                        ></v-select>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          v-model="newItem.password"
                          :readonly="true"
                          hint="One Time Password"
                          persistent-hint
                        ></v-text-field>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                  <v-btn color="blue darken-1" text @click="saveNew">Save</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

            <!-- Edit user card -->
            <v-dialog v-model="dialog" max-width="500px">
              <v-card>
                <v-card-title>
                  <span class="headline">Edit User</span>
                </v-card-title>

                <v-card-text>
                  <v-container>
                    <v-row>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          v-model="editedItem.username"
                          :readonly="true"
                          hint="Username"
                          persistent-hint
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-select
                          hint="Role"
                          persistent-hint
                          :items="roleOptions"
                          solo
                          v-model="editedItem.role"
                        ></v-select>
                      </v-col>
                    </v-row>
                  </v-container>
                </v-card-text>

                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                  <v-btn color="blue darken-1" text @click="save">Save</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
          </v-toolbar>
        </template>

        <template v-slot:item.action="{ item }">
          <v-icon small class="mr-2" @click="editItem(item)">edit</v-icon>
          <v-icon small class="mr-2" @click="deleteItem(item)">delete</v-icon>
          <v-icon>mdi-lock-reset</v-icon>
        </template>
      </v-data-table>
    </v-app>
  </div>
</template>


<script>
import SurveysService from "@/service/SurveysService.js";

export default {
  name: "user-admin-component",
  data: () => ({
    dialog: false,
    dialogAdd: false,
    user: [
      {
        text: "User Id",
        align: "left",
        value: "id"
      },
      { text: "Username", value: "username" },
      { text: "Role", value: "role" },
      { text: "Actions", value: "action", sortable: false }
    ],
    editvalues: [],
    newValues: [],
    editedItem: {
      username: "",
      id: 0,
      role: ""
    },
    newItem: {
      username: "",
      id: 0,
      role: "",
      password: "p@55w0rd",
      oneTimePass: true
    },
    users: [],
    roleOptions: ["admin", "user"]
  }),

  watch: {
    dialog(val) {
      val || this.close();
    },
    dialogAdd(val) {
      val || this.close();
    }
  },

  created() {
    this.getUsers();
  },

  methods: {
    getUsers() {
      SurveysService.listUsers()
        .then(users => {
          this.users = users;
        })
        .catch(err => {
          console.error(err);
        });
    },

    editItem(item) {
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },

    deleteItem(item) {
      let deleteUser = confirm("Are you sure you want to delete this item?");
      if (deleteUser) {
        SurveysService.deleteUser(item.id).then(() => {
          this.getUsers();
        });
      } else {
        this.close();
      }
    },

    close() {
      this.dialog = false;
      this.dialogAdd = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
      }, 300);
    },

    save() {
      SurveysService.updateUserRole(this.editedItem)
        .then( () => {
          this.getUsers();
        });
      this.close();
    },

    saveNew() {
      SurveysService.saveUser(this.newItem).then(() => {
        this.getUsers();
      });
      this.close();
    }
  }
};
</script>