import React from "react";

function CreateUser({ username, email, onChange, onCreate }) {
  return (
    <div>
      <input name="username" value={username} onChange={onChange} />
      <input name="email" value={email} onChange={onChange} />
      <button onClick={onCreate}>추가</button>
    </div>
  );
}

export default CreateUser;
