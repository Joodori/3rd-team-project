import { ref } from 'vue'
import { defineStore } from 'pinia'

export const useUserInfo = defineStore('user', () => {
    
    const user = ref(true)

    const config = ref('')

    const login = ref(false)

    const user_info = ref({
        user_id : '',
        user_name : '',
        user_birth_date: '',
        user_age : '',
        user_address : '',
        user_mobile : ''
    })


    return{
        user, config, login, user_info
    }

})