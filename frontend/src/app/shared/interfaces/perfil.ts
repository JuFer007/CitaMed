export interface Perfil {
    id?: number;
    nombre: string;
    estado: number;
}

export interface Usuario {
    id?: number;
    userName: string;
    password?: string;
    rol: string;
    activo: boolean;
}

export interface LoginResponse {
    token: string;
    usuario: string;
    nombre: string;
    perfil: string;
}