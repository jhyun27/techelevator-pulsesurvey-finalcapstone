<template>
  <!-- <transition name="modal-fade">
    <div class="modal-backdrop">
      <div
        class="modal"
        role="dialog"
        aria-labelledby="modalTitle"
        aria-describedby="modalDescription"
      >
        <header class="modal-header" id="modalTitle">
          <slot name="header">
            <h3>Editing results for {{response.studentId}}</h3>
            <button type="button" class="btn-close" @click="close" aria-label="Close modal">X</button>
          </slot>
        </header>
        <form
          href="#"
          @submit.prevent="save"
        >
        <section class="modal-body" id="modalDescription">
          <slot name="body">I'm the default body!</slot>
          <input/>
        </section>
        <section class="modal-body" id="modalDescription">
          <slot name="body">I'm the default body!</slot>
        </section>
        <section class="modal-body" id="modalDescription">
          <slot name="body">I'm the default body!</slot>
        </section>
        <section class="modal-body" id="modalDescription">
          <slot name="body">I'm the default body!</slot>
        </section>
        <section class="modal-body" id="modalDescription">
          <slot name="body">I'm the default body!</slot>
        </section>
        <section class="modal-body" id="modalDescription">
          <slot name="body">I'm the default body!</slot>
        </section>
        <section class="modal-body" id="modalDescription">
          <slot name="body">I'm the default body!</slot>
        </section>
        <section class="modal-body" id="modalDescription">
          <slot name="body">I'm the default body!</slot>
        </section>
        </form>
        <footer class="modal-footer">
          <slot name="footer">
            I'm the default footer!
            <button
              type="button"
              class="btn-green"
              @click="close"
              aria-label="Close modal"
            >Close me!</button>
          </slot>
        </footer>
      </div>
    </div>
  </transition> -->
</template>

<script>
// export default {
//   name: "modal",
//   props: {
//     show: Boolean,
//     response: Object
//   },
//   methods: {
//     close() {
//       this.$emit("close");
//     },
//     save() {
//       // Some save logic goes here...

//       this.close();
//     }
//   },
//   data() {
//     return { title: "", body: "" };
//   },
//   mounted: function() {
//     document.addEventListener("keydown", e => {
//       if (this.show && e.keyCode == 27) {
//         this.close();
//       }
//     });
//   }
// };
</script>

<style>
/* .modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background: white;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  width: 400px;
  height: 400px;
  flex-direction: column;
}

.modal-header,
.modal-footer {
  padding: 15px;
  display: flex;
}

.modal-header {
  border-bottom: 1px solid #eeeeee;
  color: white;
  justify-content: space-between;
}

.modal-footer {
  border-top: 1px solid #eeeeee;
  justify-content: flex-end;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
}

.btn-close {
  border: none;
  font-size: 25px;
  padding: 5px;
  cursor: pointer;
  font-weight: bold;
  color: white;
  background: transparent;
}

.btn-green {
  border: 1px solid #4aae9b;
  border-radius: 2px;
} */
</style>