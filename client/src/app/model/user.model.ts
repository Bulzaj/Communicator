export class UserModel {

  id: number;
  username: string;
  createdAt: string;

  constructor(id: number, username: string, createdAt: string) {
    this.id = id;
    this.username = username;
    this.createdAt = createdAt;
  }
}
