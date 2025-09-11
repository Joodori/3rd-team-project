import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useUserInfo = defineStore('user', () => {
    
    const user = ref(true)

    const login = ref(false)

    const customer = ref({
        user_id : '',
        user_password: '',
        user_name : '',
        user_age : '',
        user_mobile : ''
    })


    return{
        user, login, customer
    }

})