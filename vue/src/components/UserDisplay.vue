<template>
  <div class="main-auto-x">
      <div class="userdisplay">
        <table>
          <th></th>
          <tr>{{user.username}}</tr>
          </table>
      </div>
  </div>
</template>

<script>
export default {
  name: "user-display",
  props: ["user"],

}
</script>

<style>
.userdisplay{
  background-color: rgb(233, 220, 220);
  display: flex;
  flex-direction: column;
  /* justify-content: space-evenly; */
  align-items: center;
  border: 1px black solid;
  border-radius: 6px;
  padding: .001rem;
  margin: 1rem;
}

</style>
